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

@WebServlet(name = "wwqtest2", urlPatterns = { "/test2" })
public class UnsafeCountingFactorizer implements Servlet {

	private long count = 0;

    public long getCount() {
        return count;
    }
    
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
        ++count;
        encodeIntoResponse(res, factors,count);

	}
	
	void encodeIntoResponse(ServletResponse res, BigInteger[] factors,long count) {
		PrintWriter pw;
		try {
			pw = res.getWriter();
			String str_factors = factors.toString();
			String str_count = String.valueOf(count);
			pw.println("factors : " + str_factors);
			pw.println("count : " + str_count);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[] { i };
    }

}
