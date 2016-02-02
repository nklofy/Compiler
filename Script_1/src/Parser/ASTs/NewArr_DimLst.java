package Parser.ASTs;

import java.util.*;
import Parser.*;

public class NewArr_DimLst extends AST {
	LinkedList<ExprCalc_Add> dims;
	public boolean addDim(ExprCalc_Add ast){
		if(this.dims==null){
			this.dims=new LinkedList<ExprCalc_Add>();
		}
		this.dims.add(ast);
		return true;
	}
}
