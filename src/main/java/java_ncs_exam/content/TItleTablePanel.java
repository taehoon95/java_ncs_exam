package java_ncs_exam.content;

import javax.swing.SwingConstants;

import java_ncs_exam.Abstract.AbstractCustomTablePanel;
import java_ncs_exam.dto.Title;
import java_ncs_exam.service.TitleService;

@SuppressWarnings("serial")
public class TItleTablePanel extends AbstractCustomTablePanel<Title> {
	
	private TitleService service;
	
	
	@Override
	protected void setAlignAndWidth() {
		
		 //컬럼내용 정렬 
		 setTableCellAlign(SwingConstants.CENTER, 0, 1); 
		 
		 //컬럼별 너비 조정
		 setTableCellWidth(100, 250);
		 
	}

	@Override
	public Object[] toArray(Title t) {
		String tno = String.format("T%03d", t.gettNo());
		return new Object[] {
				tno
				,t.gettName()};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"번호","직책명"};
	}

	@Override
	public void initList() {
		service = new TitleService();
		list = service.showTitles();
	}

	public void setService(TitleService service) { 
		this.service = service;
	}

	@Override
	public Title getItem() {
		int row = table.getSelectedRow();
		String temp = (String)table.getValueAt(row, 0);
		int tno = Integer.parseInt(temp.replace("T", ""));
		if(row == -1) {
			throw new IndexOutOfBoundsException();
		}
		System.out.println(list.get(list.indexOf(new Title(tno))));
		return list.get(list.indexOf(new Title(tno)));
	}

}
