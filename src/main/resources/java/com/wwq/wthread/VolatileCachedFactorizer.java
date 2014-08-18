package com.wwq.wthread;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "wwqtest7", urlPatterns = { "/test7" })
public class VolatileCachedFactorizer implements Servlet {

	private BigInteger add1 = BigInteger.valueOf(100L);
	private BigInteger[] addList = new BigInteger[]{add1};
	private volatile OneValueCache cache = new OneValueCache(add1, addList);
	private static long count = 0L;
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

	@SuppressWarnings("unused")
	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        BigInteger[] factorsFirst = null;
        if (factors != null)
        	factorsFirst = Arrays.copyOf(factors,factors.length);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        encodeIntoResponse(res, factors, factorsFirst);

	}
	
	void encodeIntoResponse(ServletResponse resp, BigInteger[] factors, BigInteger[] factorsFirst) {
		PrintWriter pw;
		try {
			pw = resp.getWriter();
			if(factorsFirst != null)
				for(BigInteger factorFirst:factorsFirst){
					pw.println("factor_First : " + factorFirst);
				}
			for(BigInteger factor:factors){
				pw.println("factor : " + factor);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    BigInteger extractFromRequest(ServletRequest req) {
    	count++;
    	System.out.println(count);
        return BigInteger.valueOf(count);
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }

}
