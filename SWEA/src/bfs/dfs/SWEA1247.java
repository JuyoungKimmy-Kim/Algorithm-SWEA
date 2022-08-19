package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class SWEA1247 {
 
    static int T,N, ans;
    static int hy,hx,oy,ox;
    static List<int[]> customer;
    static int[] tgt;
    static boolean[] selected;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
        StringTokenizer st=null;
         
        T=Integer.parseInt(br.readLine());
         
        for (int tc=1; tc<=T; tc++) {
            customer=new ArrayList<>();
            N=Integer.parseInt(br.readLine());
             
            st=new StringTokenizer (br.readLine());
             
            oy=Integer.parseInt(st.nextToken());
            ox=Integer.parseInt(st.nextToken());
            hy=Integer.parseInt(st.nextToken());
            hx=Integer.parseInt(st.nextToken());
             
            for (int i=0; i<N; i++) {
                int y=Integer.parseInt(st.nextToken());
                int x=Integer.parseInt(st.nextToken());
                 
                customer.add(new int[] {y,x});
            }
             
            tgt=new int[N];
            selected=new boolean[N];
            ans=Integer.MAX_VALUE;
             
            perm(0);
            System.out.println("#"+tc+" "+ans);
        }
    }
     
    private static void perm (int tgtIdx) {
        if (tgtIdx==N) {
            findDist();
            return ;
        }
         
        for (int i=0; i<N; i++) {
            if (selected[i]) continue;
             
            selected[i]=true;
            tgt[tgtIdx]=i;
            perm (tgtIdx+1);
            selected[i]=false;
        }
    }
     
    private static void findDist () {
        int y=oy; int x=ox;
         
        int dist=0;
        for (int i=0; i<N; i++) {
            int idx=tgt[i];
             
            int ny=customer.get(idx)[0];
            int nx=customer.get(idx)[1];
             
            dist+=(Math.abs(ny-y) + Math.abs(nx-x));
             
            y=ny; x=nx;
        }
         
        dist+=(Math.abs(hy-y)+Math.abs(hx-x));
         
        if (dist<ans) ans=dist;
    }
 
}