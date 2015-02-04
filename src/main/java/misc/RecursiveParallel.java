//package misc;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//
///**
// * 递归算法并行化
// * 
// * 概念陈述，非可运行代码
// * 
// * @author Destiny
// *
// */
//public class RecursiveParallel {
//
//	/**
//	 * 将一组任务串行执行
//	 * @param elements
//	 */
//	public void sequentialProcess(List<Element> elements) {
//		for(Element element : elements) {
//			process(element);
//		}
//	}
//	
//	/**
//	 * 将一组任务并行执行
//	 * @param es
//	 * @param elements
//	 */
//	public void parallelProcess(ExecutorService es, List<Element> elements) {
//		for(final Element element : elements) {
//			es.execute(new Runnable() {
//				@Override
//				public void run() {
//					process(element);
//				}
//			});
//		}
//	}
//	
//	/**
//	 * 串行化的DFS处理过程
//	 * @param nodes
//	 * @param results
//	 */
//	public void sequentialDFSProcess(List<Node> nodes, Collection<Result> results) {
//		for(Node node : nodes) {
//			// 处理单个节点，将处理结果放到results集合中，process的返回值为新的待处理节点列表
//			List<Node> newNodes = process(node, results);
//			// 递归调用
//			sequentialDFSProcess(newNodes, results);
//		}
//	}
//	
//	/**
//	 * 并行化的DFS处理过程
//	 * @param es
//	 * @param nodes
//	 * @param results
//	 */
//	public void parallelDFSProcess(ExecutorService es, List<Node> nodes, Collection<Result> results) {
//		for(Node node : nodes) {
//			
//		}
//	}
//	
//}
