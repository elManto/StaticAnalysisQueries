import io.shiftleft.codepropertygraph.Cpg
import io.shiftleft.codepropertygraph.generated.Operators
import io.shiftleft.codepropertygraph.generated.nodes.Call
import io.shiftleft.semanticcpg.language._

@main def main(): List[Call] = {
  (cpg: Cpg)
    .call("LIBSSH2_ALLOC")	// Adapt to the memory allocation API
    .filter { mallocCall =>
      mallocCall.argument(2) match {
        case subCall: Call =>
          subCall.name == Operators.addition || subCall.name == Operators.multiplication
        case _ => false
      }
    }
    .l
}
