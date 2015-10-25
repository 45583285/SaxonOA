package sl.service;
import java.util.List;

import sl.pageModel.Common;
import sl.pageModel.Contents;
import sl.pageModel.Menus;
public interface ContentsServiceI extends BaseServiceI<Contents> {

	public List<Contents> searchCommon(Common common);

	public List<Menus> getCommonById(Common common);

	public Common getPages(Common common);


}
