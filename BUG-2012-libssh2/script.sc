import io.shiftleft.codepropertygraph.Cpg
import io.shiftleft.codepropertygraph.generated.Operators
import io.shiftleft.codepropertygraph.generated.nodes.Call
import io.shiftleft.semanticcpg.language._

@main def main(payload: String) = {
	importCpg(payload)
	
	cpg.call(".*alloc*.|.*ALLOC*.").argument.isCallTo("<operator>.addition").p
	//cpg.call(".*alloc*.|.*ALLOC*.").argument.isCallTo("<operator>.addition").count.p
}
