import edu.polytechnique.xvm.asm.opcodes.*;
import java.util.Optional;

import edu.polytechnique.mjava.ast.LValue;

@SuppressWarnings("unused")
public final class IAssign extends AbstractInstruction {
  public final Optional<LValue<AbstractExpr>> lvalue; // (optional) left-value
  public AbstractExpr           rvalue; // right-value (expression)

  public IAssign(Optional<String> lvalue, AbstractExpr rvalue) {
    this.lvalue = lvalue.map((x) -> new LValue<AbstractExpr>(x));
    this.rvalue = rvalue;
  }
  
  public IAssign(LValue<AbstractExpr> lvalue, AbstractExpr rvalue) {
    this.lvalue = Optional.of(lvalue);
    this.rvalue = rvalue;
  }

  @Override
  public void codegen(CodeGen cg) {
    this.rvalue.codegen(cg);
    if (lvalue.isPresent()) {
      LValue<AbstractExpr> v = lvalue.get();
      cg.pushInstruction(new WFR(cg.getOffset(v.name)));
    }
    else {cg.pushInstruction(new POP());}
  }
}
