package com.example.override;

import cpw.mods.fml.relauncher.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;


public class ASM  implements IClassTransformer
{
	public byte[] transform(String name, byte[] bytes)
	{
		// Only transform rj.class
		if (!name.equals("rj")) {
			return bytes;
		}

		try {
			// Read the class
			ClassNode classNode = new ClassNode();
			ClassReader cr = new ClassReader(bytes);
			cr.accept(classNode, 0);

			// Iterate methods
			for (Object mObj : classNode.methods) {
				MethodNode method = (MethodNode) mObj;

				if (method.name.equals("r") && method.desc.equals("()Ljava/util/List;")) {

					InsnList insns = method.instructions;

					// Iterate instructions
					for (AbstractInsnNode insn = insns.getFirst(); insn != null; ) {
						AbstractInsnNode next = insn.getNext();

						// Look for List.add call
						if (insn.getOpcode() == org.objectweb.asm.Opcodes.INVOKEINTERFACE) {
							MethodInsnNode m = (MethodInsnNode) insn;

							if (m.owner.equals("java/util/List") &&
								m.name.equals("add") &&
								m.desc.equals("(Ljava/lang/Object;)Z")) {

								// Check if previous instruction is ts.c(...) → confirms L12 block
								AbstractInsnNode prev = insn.getPrevious();
								if (prev != null && prev.getOpcode() == org.objectweb.asm.Opcodes.INVOKEVIRTUAL) {
									MethodInsnNode tsCall = (MethodInsnNode) prev;
									if (tsCall.owner.equals("ts") && tsCall.name.equals("c")) {

										// Remove the entire L12 block

										// 1. remove List.add
										insns.remove(insn);

										// 2. remove pop
										AbstractInsnNode pop = next;
										if (pop != null && pop.getOpcode() == org.objectweb.asm.Opcodes.POP) {
											insns.remove(pop);
										}

										// 3. remove instructions that built the argument (aload1, getstatic, iload5, aaload, iload6, invokevirtual ts.c)
										int toRemove = 6;
										AbstractInsnNode cur = prev;
										while (cur != null && toRemove-- > 0) {
											AbstractInsnNode before = cur.getPrevious();
											insns.remove(cur);
											cur = before;
										}

										// Done with L12
										break;
									}
								}
							}
						}

						insn = next;
					}
				}
			}

			// Write the modified class
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			classNode.accept(cw);
			return cw.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
			return bytes; // on error, return original class
		}

	}
}
