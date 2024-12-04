import edu.polytechnique.xvm.asm.opcodes.*;

public final class IWhile extends AbstractInstruction {
  public final AbstractExpr        condition; // loop condition
  public final AbstractInstruction body     ; // loop body

  public IWhile(AbstractExpr condition, AbstractInstruction body) {
    this.condition = condition;
    this.body = body;
  }

  @Override
  public void codegen(CodeGen cg) {
    String lbl1 = CodeGen.generateLabel();
    String lbl2 = CodeGen.generateLabel();

    cg.pushLabel(lbl1);
    this.condition.codegen(cg);
    cg.pushInstruction(new GTZ(lbl2));
    this.body.codegen(cg);
    cg.pushInstruction(new GTO(lbl1));
    cg.pushLabel(lbl2);  
  }
}
