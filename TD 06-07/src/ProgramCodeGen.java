import java.util.List;

import edu.polytechnique.mjava.ast.TProcDef;
import edu.polytechnique.mjava.ast.VarDecl;
import edu.polytechnique.xvm.asm.opcodes.GSB;
import edu.polytechnique.xvm.asm.opcodes.PUSH;
//import edu.polytechnique.xvm.asm.opcodes.POP;
//import edu.polytechnique.xvm.asm.opcodes.PRX;
//import edu.polytechnique.xvm.asm.opcodes.PUSH;
//import edu.polytechnique.xvm.asm.opcodes.PXR;
import edu.polytechnique.xvm.asm.opcodes.RET;
import edu.polytechnique.xvm.asm.opcodes.STOP;

public class ProgramCodeGen {
  public final CodeGen cg = new CodeGen();

  public static String labelOfProcName(String name) {
    return String.format("__%s", name);
  }

  @SuppressWarnings("unused")
  public void codegen(TProcDef<AbstractExpr, AbstractInstruction> proc) {

    cg.pushLabel(labelOfProcName(proc.getName()));

    final List<VarDecl> args = proc.getArgs(); // Proc. arguments
    final List<VarDecl> locals = proc.getLocals(); // Proc. locals
    final AbstractInstruction is = proc.getBody(); // Proc. body

    for (int i = 0; i < args.size(); i++) {cg.pushLocalVariable(args.get(i).getName(), i - args.size());}

    for (int i = 0; i < locals.size(); i++) {
      cg.pushLocalVariable(locals.get(i).getName(), i + 2);
      cg.pushInstruction(new PUSH(0)); //valeur par défaut de la variable
    }
    is.codegen(cg);
    cg.pushInstruction(new RET());
  }
  
  

  public void codegen(List<TProcDef<AbstractExpr, AbstractInstruction>> procs) {
    for (TProcDef<AbstractExpr, AbstractInstruction> proc : procs)
      this.codegen(proc);
  }

  public ProgramCodeGen() {
    this.cg.pushInstruction(new GSB(labelOfProcName("main")));
    this.cg.pushInstruction(new STOP());
  }
}
