package Parser.ASTs;

import java.util.*;
import Parser.*;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class NewArr_DimLst extends AST {
	LinkedList<ExprCalc_Add> dims;
	String rst_val;
	
	public boolean addDim(ExprCalc_Add ast){
		if(this.dims==null){
			this.dims=new LinkedList<ExprCalc_Add>();
		}
		this.dims.add(ast);
		return true;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		this.rst_val="";
		for(ExprCalc_Add dim:dims){
			dim.genCode(codegen);
			this.rst_val+=dim.rst_val+",";
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		for(ExprCalc_Add dim:dims){
			if(!dim.genSymTb(codegen))
				return false;
			dim.ref_type="int";
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		for(ExprCalc_Add dim:dims){
			if(!dim.checkType(codegen))
				return false;
		}
		return true;
	}
}
