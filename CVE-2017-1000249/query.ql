import cpp
import semmle.code.cpp.dataflow.DataFlow


from FunctionCall fc, Expr src
where fc.getTarget().getName() = "memcpy"
    and DataFlow::localFlow(DataFlow::exprNode(src), 
    DataFlow::exprNode(fc.getArgument(2)))
select src
