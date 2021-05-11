import cpp
import semmle.code.cpp.controlflow.StackVariableReachability


predicate isFreeExpr(Expr e, StackVariable v) {
  exists(VariableAccess va | va.getTarget() = v |
    exists(FunctionCall fc | fc = e |
      fc.getTarget().hasGlobalOrStdName("g_free") and
      va = fc.getArgument(0)
    )
  )
}


predicate isDerefExpr(Expr e, StackVariable v) {
  v.getAnAccess() = e and dereferenced(e)
}


predicate isDerefByCallExpr(Call c, int i, VariableAccess va, StackVariable v) {
  v.getAnAccess() = va and
  va = c.getAnArgumentSubExpr(i) and
  not c.passesByReference(i, va) and
  (c.getTarget().hasEntryPoint() implies isDerefExpr(_, c.getTarget().getParameter(i)))
}

class UseAfterFreeReachability extends StackVariableReachability {
  UseAfterFreeReachability() { this = "UseAfterFree" }

  override predicate isSource(ControlFlowNode node, StackVariable v) { isFreeExpr(node, v) }

  override predicate isSink(ControlFlowNode node, StackVariable v) { isDerefExpr(node, v) }

  override predicate isBarrier(ControlFlowNode node, StackVariable v) {
    definitionBarrier(v, node) or
    isFreeExpr(node, v)
  }
}

from UseAfterFreeReachability r, StackVariable v, Expr free, Expr e
where r.reaches(free, v, e)
select e, "Memory pointed to by '" + v.getName().toString() + "' may have been previously freed $@",
  free, "here"
