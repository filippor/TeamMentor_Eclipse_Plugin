package tm.eclipse.ui.views;

import java.util.List;

import org.eclipse.swt.layout.*;
//import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
//import org.eclipse.swt.widgets.*;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.ui.part.ViewPart;

import tm.eclipse.swt.Composite_Add;
import tm.eclipse.swt.Control_Get;
import tm.eclipse.swt.Control_Self;
import tm.eclipse.swt.Control_Set;
import tm.eclipse.swt.Mouse;
import tm.eclipse.swt.controls.*;

public class Eclipse_Panel extends ViewPart 
{
	public static final String ID = "tm.eclipse.ui.views.Eclipse_Panel";
	public Composite composite;
	public Mouse 					mouse;
	public Composite_Add<Composite> add;
	public Control_Get  <Composite> get;
	public Control_Set  <Composite> set;
	public Control_Self <Composite> self;
	
	//required implementations
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) 
	{
		composite  = new Composite(parent);
		 
		mouse = composite.mouse;
		add   = composite.add;
		set   = composite.set;
		get   = composite.get;
		self   = composite.self;
	}
	public void setFocus() 
	{
	}		
	public void title(final String title)
	{
		UIThreadRunnable.syncExec(composite.getDisplay(), new VoidResult() { public void run()
			{
				Eclipse_Panel.super.setPartName(title);
			}
			});
		
	}
	
	//helper methods
	/*
	public Browser add_Browser()
	{
		return add_WebBrowser();	    	    
	}	
	public Button  add_Button()
	{
		return Button.add_Button(composite, SWT.NONE);	    	    
	}
	public Button  add_Button(int style)
	{
		return Button.add_Button(composite, style);	    	    
	}
	public Button  add_Button(String text)
	{
		return Button.add_Button(composite, SWT.NONE, text);
	}
	public Text    add_Text()
	{
		return Text.add_Text(composite);	    	    
	}
	public Text    add_Text(int style)
	{
		return Text.add_Text(composite, style);	    	    
	}
	public Text    add_Text(String text)
	{
		return Text.add_Text(composite, text);	    	    
	}
	public Text    add_Text_Search()
	{
		return Text.add_Text_Search(composite);	    	    
	}
	public Tree 	  add_Tree()
	{	
		return Tree.add_Tree(composite);	  			    	   
	}
	public Tree 	  add_Tree(int style)
	{	
		return Tree.add_Tree(composite, style);	  			    	   
	}
	public Browser add_WebBrowser()
	{
		return Browser.add_Browser(composite, this);			    	   
	}
	
	*/
	public Eclipse_Panel clear()
	{
		composite.self.clear();
		return this;
		
/*		UIThreadRunnable.syncExec(composite.getDisplay(), new VoidResult() { public void run() 
			{
				for(Control control : controls())
					control.dispose();
			}});*/		
	}
	public Eclipse_Panel close()
	{		
		UIThreadRunnable.syncExec(composite.getDisplay(), new VoidResult() { public void run() 
			{
				Eclipse_Panel.this.getSite().getPage().hideView(Eclipse_Panel.this);
			}});
		return this;
	}
	public Eclipse_Panel close_InSeconds(final int seconds)
	{
		return close_InMiliSeconds(seconds * 1000);
	}
	public Eclipse_Panel close_InMiliSeconds(final int miliSeconds)
	{
		Thread thread_toClose = new Thread(new Runnable() { public void run()
			{		
				try 
				{
					Thread.sleep(miliSeconds);
				}
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				close();		
			}});
		thread_toClose.start();		
		return this;
	}
	public List<Control> controls()
	{
		return composite.self.controls();
		
	/*	return UIThreadRunnable.syncExec(composite.getDisplay(), new Result<List<Control>>() { public List<Control> run() 
					{
						return Arrays.asList(composite.getChildren());
					}});*/
	}	
	public <T> T 		 control(Class<T> clazz)
	{
		return composite.self.control(clazz);	
	}
	
	public Layout 		 layout()
	{
		return UIThreadRunnable.syncExec(composite.getDisplay(), new Result<Layout>() { public Layout run()
			{
				return composite.getLayout();
			}});
	}
	
	public Eclipse_Panel layout(final Layout newLayout)
	{
		UIThreadRunnable.syncExec(composite.getDisplay(), new VoidResult() { public void run()
			{
				composite.setLayoutData(null);
				composite.setLayout(newLayout);
				composite.layout(true);	
			}});
		return this;
	}
	
	public Eclipse_Panel layout_Fill(final Layout newLayout)
	{
		return layout(new FillLayout());
	}
	public Eclipse_Panel layout_Form(final Layout newLayout)
	{
		return layout(new FormLayout());
	}
	public Eclipse_Panel layout_Grid(final Layout newLayout)
	{
		return layout(new GridLayout());
	}
	public Eclipse_Panel layout_Row(final Layout newLayout)
	{
		return layout(new RowLayout());
	}	
	public Eclipse_Panel sleep(int miliseconds)
	{
		try 
		{
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) 
		{ 
			e.printStackTrace();
		}
		return this;
	}
	public Eclipse_Panel refresh()
	{
		UIThreadRunnable.syncExec(composite.getDisplay(), new VoidResult() { public void run() 
					{
						composite.layout(true);
					}});
		return this;
	}
}