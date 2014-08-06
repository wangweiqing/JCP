package com.wwq.wthread;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
@WebServlet(name = "wwqtest", urlPatterns = { "/test1" })
public class StatelessFactorizer implements Servlet {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		BigInteger i = extractFromRequest(req);
		BigInteger[] factors = factor(i);
		PrintWriter pw = res.getWriter();
        pw.print(factors);

	}
	
	private BigInteger extractFromRequest(ServletRequest req) {
        // TODO Auto-generated method stub
        /*String num = req.getParameter("factor");*/
		return new BigInteger("7");
    }
	
	 private BigInteger[] factor(BigInteger i) {
	        // TODO Auto-generated method stub
		 return new BigInteger[] { i };
	    }

}
