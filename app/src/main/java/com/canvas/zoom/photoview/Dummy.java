package com.canvas.zoom.photoview;

public class Dummy {
/*    public class mainactivity extends actionbaractivity {
        photoview mphotoview;

        @override
        protected void oncreate(bundle asavedinstancestate) {
            super.oncreate(asavedinstancestate);
            mphotoview = new photoview(this);
            mphotoview.setmaximumscale(12);
            setcontentview(mphotoview);
            mphotoview.setimageresource(r.drawable.vm_app_icon);
            if (asavedinstancestate != null) {
                rectf therect = asavedinstancestate.getparcelable("rect");
                if (therect != null) {
                    matrix thematrix = new matrix();
                    thematrix.setscale(therect.bottom, therect.left, therect.right, therect.top);
                    mphotoview.setdisplaymatrix(thematrix);
                }
            }
        }

        @override
        protected void onsaveinstancestate(final bundle outstate) {
            super.onsaveinstancestate(outstate);
            rectf therect = mphotoview.getdisplayrect();
            if (therect != null) {
                outstate.putparcelable("rect", therect);
            }
        }
    }

    @override
    protected void onsaveinstancestate(final bundle outstate) {
        super.onsaveinstancestate(outstate);
        matrix thematrix = mphotoview.getdisplaymatrix();
        float[] thefloat = new float[9];
        thematrix.getvalues(thefloat);
        rectf therect = mphotoview.getdisplayrect();
        if (therect != null) {
            if (therect.left > (mviewwidth / 2) || (therect.left >= 0)) {
                therect.left = 0;
            } else {
                therect.left = (therect.left - (mviewwidth / 2)) / mscreenbase;
            }
            if (therect.top > (mviewheight / 2) || (therect.top >= 0)) {
                therect.top = 0;
            } else {
                therect.top = (therect.top - (mviewheight / 2)) / mscreenbase;
            }
            outstate.putparcelable("rectf", therect);
            outstate.putfloat("zoomlevel", mphotoview.getscale());
        }
    }

    @override
    protected void oncreate(final bundle asavedinstancestate) {
        super.oncreate(asavedinstancestate);
        mphotoview = new photoview();
        mphotoview.setmaximumscale(16);
        setcontentview(mphotoview);
        mphotoview.setimageresource(r.drawable.vm_app_icon);
        mphotoview.getviewtreeobserver().addonpredrawlistener(new viewtreeobserver.onpredrawlistener() {
            public boolean onpredraw() {
                mphotoview.getviewtreeobserver().removeonpredrawlistener();
                mviewheight = mphotoview.getmeasuredheight();
                mviewwidth = mphotoview.getmeasuredwidth();
                matrix thematrix = mphotoview.getdisplaymatrix();
                thematrix.getvalues(mbasematrixvalues);
                mscreenbase = mbasematrixvalues[0];
                int thewidth = mphotoview.getwidth();
                log.e(tag, thewidth + "");
                if (asavedinstancestate != null) {
                    float[] thefloats = new float[9];
                    float thezoom = asavedinstancestate.getfloat("zoomlevel");
                    rectf therect = asavedinstancestate.getparcelable("rectf");
                    thefloats[0] = thezoom;
                    thefloats[4] = thezoom;
                    thefloats[2] = (therect.left * mscreenbase) - (thezoom * mbasematrixvalues[2]) + (mviewwidth / 2); //left                     thefloats[ 5 ] = ( therect.top * mscreenbase ) - ( thezoom * mbasematrixvalues[ 5 ] ) + ( mviewheight / 2 ); //top                     thefloats[ 8 ] = (float) 1.0;                      thefloats = checkboundaries( thezoom, thefloats, therect );                      thematrix.setvalues( thefloats );                     mphotoview.setdisplaymatrix( thematrix ); //sets msuppmatrix in photoviewattacher                      matrix theimageviewmatrix = mphotoview.getdisplaymatrix(); //gets new mdrawmatrix                     mphotoview.setimagematrix( theimageviewmatrix ); //and applies photoview (catches out of boundaries problems)                 }                 return true;             }         } );     }          private float[] checkboundaries(final float azoom, float[] afloats, final rectf arect )         {             if( azoom == 1.0 ) //if zoom way out             {                 afloats[ 2 ] = 0;                 afloats[ 5 ] = 0;                 return afloats;             }              themaxleftvalue = ( ( mviewheight * azoom ) - mviewwidth + ( azoom * mbasematrixvalues[ 2 ] ) );             themaxtopvalue = ( ( mviewwidth * azoom ) - mviewheight + ( azoom * mbasematrixvalues[ 5 ] ) );             if( math.abs( afloats[ 2 ] ) > ( themaxleftvalue ) )             {                 afloats[ 2 ] = -math.abs( themaxleftvalue ) + 10;             }             else if( math.abs( afloats[ 2 ] ) < ( azoom * mbasematrixvalues[ 2 ] ) )             {                 afloats[ 2 ] = -( azoom * mbasematrixvalues[ 2 ] );             }              if( math.abs( afloats[ 5 ] ) > ( themaxtopvalue ) )             {                 afloats[ 5 ] = -math.abs( themaxtopvalue ) + 10;             }             else if( math.abs( afloats[ 5 ] ) < ( azoom * mbasematrixvalues[ 5 ] ) )             {                 afloats[ 5 ] = -( azoom * mbasematrixvalues[ 5 ] );             }              if( afloats[ 2 ] > 0 )                 afloats[ 2 ] = -( mviewwidth / 2 );             else if( afloats[ 5 ] > 0 )                 afloats[ 5 ] = -( mviewheight / 2 );              return afloats;         }

                }

            }
        }
    }*/
}