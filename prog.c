#include <stdio.h>
#include <stdlib.h>
// M hv Nf and Ni
// size Nx Ny
// Nf = (2Nx + 2Ny - 4 )
// NI(i,j) = Nf + (j-1)(Nx-2) + i
// i 0.....nx-1
// j ny-2............ny-2

// tab[2][ny]

int main()
{
    int a=1,b=6,c=1,d=6,Nx=6,Ny=6,hx,hy;
    hx = (b-a)/(Nx-1);
    hy=(d-c)/(Ny-1);

    int X[Nx*Ny][2];


    int mx,my;
// later
    for (int j = 1; j < Ny; j++)
    {
        M[Nx][j] = vector([mx,0]);
        mx=mx+hx;
    }

    for (int i = Nx-1; i > 1; i--)
    {
        M[i][Ny] = vector([a,my+hy]);
        my = my+hy;

    }
    
    mx = a - hx;
    for (int j = Ny-1; j > 1; j--)
    {
        M[1][j] = vector([mx,b]);
        mx = mx-hx;
    }
    my = b - hy ;
    for (int i = 2 ; i < Nx-1; i++)    {
        
        M[i] [1] = vector([0,my]);
    }


    // TODO test 

    mx= hx ; my = hy; int k = 0 ;

    for (int i = Nx-1; i >2 ; i--){

        for (int j = 2; j < Ny-1; j++)
        {
            
            X [k][0] = vector([i,j])

            X[k][1] = vector([mx,my]);
            mx = mx + hx;

            k++;
        }
        
        mx = hx;
        my = my + hy ;
    return 0;
}
