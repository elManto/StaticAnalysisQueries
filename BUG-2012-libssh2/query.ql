import cpp
import semmle.code.cpp.dataflow.DataFlow

  from MacroInvocation mi, Macro alloc, Expr src, AddExpr add
  where alloc.hasName("LIBSSH2_ALLOC") 
         and mi.getMacro() = alloc
         and mi.getExpr().(ExprCall).getArgument(0) = add
         and  DataFlow::localFlow(DataFlow::exprNode(src), 
              DataFlow::exprNode(mi.getExpr().(ExprCall).getArgument(0))
              )
         

  select src.getLocation(), mi
