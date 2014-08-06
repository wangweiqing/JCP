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

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
@WebServlet(name = "wwqtest4", urlPatterns = { "/test4" })
public class CachedFactorizer implements Servlet {

	@GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;
    @GuardedBy("this") private long hits;
    @GuardedBy("this") private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
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
        BigInteger[] factors = null;
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoResponse(res, factors);
	}
	void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
		PrintWriter pw;
		try {
			pw = resp.getWriter();
			for(BigInteger factor:factors){
				pw.println("factor : " + factor);
			}
			pw.println("hist : "+this.getHits());
			pw.println("cacheHits"+this.getCacheHitRatio());
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
        return new BigInteger[]{i};
    }
}
