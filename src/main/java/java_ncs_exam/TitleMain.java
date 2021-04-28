package java_ncs_exam;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import java_ncs_exam.Abstract.AbstractContentPanel;
import java_ncs_exam.Abstract.AbstractCustomTablePanel;
import java_ncs_exam.Abstract.AbstractManagerUI;
import java_ncs_exam.content.TItleTablePanel;
import java_ncs_exam.content.TitlePanel;
import java_ncs_exam.dto.Title;
import java_ncs_exam.service.TitleService;

@SuppressWarnings("serial")
public class TitleMain extends AbstractManagerUI<Title> {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TitleMain frame = new TitleMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private TitleService service;
	
	public TitleMain() {
		setTitle("직책 관리");}
	
	@Override
	protected void setService() {
		service = new TitleService();
	}

	@Override
	protected void tableLoadData() {
		((TItleTablePanel)pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<Title> createContentPanel() {
		return new TitlePanel();
	}

	@Override
	protected AbstractCustomTablePanel<Title> createTablePanel() {
		return new TItleTablePanel();
	}


	@Override
	protected void actionPerformedMenuUpdate() {
		Title updateTitle = pList.getItem();
		pContent.setItem(updateTitle);
		btnAdd.setText("수정");
		((TitlePanel) pContent).getTfTitleNo().setEditable(false);
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Title delTitle = pList.getItem();
		service.removeTitle(delTitle);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delTitle + "이(가) 삭제 되었습니다.");
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Title temp = pList.getItem();
		Title updateTitle = pContent.getItem();
		service.modifyTitle(updateTitle);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, temp +"이(가) "+ updateTitle + "로 변경되었습니다.");
		((TitlePanel) pContent).getTfTitleNo().setEditable(true);	
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title title = pContent.getItem();
		service.addTitle(title);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, title + "이(가) 추가되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
	}

	
}