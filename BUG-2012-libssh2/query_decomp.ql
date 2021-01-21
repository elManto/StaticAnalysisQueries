import cpp
import semmle.code.cpp.dataflow.DataFlow


from ExprCall call, Expr src, AddExpr add
where call.getArgument(0) = add
      and  DataFlow::localFlow(DataFlow::exprNode(src),
            DataFlow::exprNode(call.getArgument(0))
            )
select call
