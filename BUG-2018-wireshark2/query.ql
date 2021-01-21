import cpp
import semmle.code.cpp.dataflow.TaintTracking

class Config extends DataFlow::Configuration {
    Config() { this = "DoubleFreeFlows" }
  
    override predicate isSource(DataFlow::Node source) {
          exists (Expr e |
            source.asExpr() = e)
    }
    override predicate isSink(DataFlow::Node sink) {
          exists (FunctionCall fc | 
              fc.getTarget().hasQualifiedName("g_free") and
              sink.asExpr() = fc.getArgument(0).
                    (VariableAccess).getQualifier().
                    (VariableAccess).getQualifier()
                )
    }
  }
  
  from Config cfg, DataFlow::PathNode source, DataFlow::PathNode sink
  where cfg.hasFlowPath(source, sink)
  select source, sink, "Flow to free"
