package sl.service;
import sl.pageModel.DataGrid;
import sl.pageModel.Filein;

public interface FileinServiceI extends BaseServiceI<Filein> {

//	public void save(Filein filein);

//	public DataGrid datagrid(Filein filein);

	public void submit(Filein filein);


	public Filein getByTaskId(String uuid);

	public DataGrid queryHistoricActivityInstance(String uuid);

//	public Filein getById(String uuid);

}
