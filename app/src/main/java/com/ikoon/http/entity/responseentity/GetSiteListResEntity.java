package com.ikoon.http.entity.responseentity;

import java.util.List;

/**
 * @author MrKong
 * @date 2018/6/14
 * @description
 */

public class GetSiteListResEntity
{
    
    /**
     * Data : {"TotalSite":477,"SiteModel":[{"SiteType":4,"SiteId":574,"SiteName":"海伦春天三期工地","Address":"湖北省;武汉市;汉阳区,距离神龙驾校第五招生培训处42米","Distance":null,"DepartmentId":93},{"SiteType":4,"SiteId":575,"SiteName":"6号线香港路站","Address":"湖北省;武汉市;江岸区,距离上海家常菜23米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":579,"SiteName":"7号线三阳路站A","Address":"湖北省;武汉市;江岸区,距离武汉利行旅行社13米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":580,"SiteName":"8号线竹叶山站","Address":"湖北省;武汉市;江岸区,距离天梨SOHO102米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":581,"SiteName":"8号线幸福大道","Address":"湖北省;武汉市;江岸区,距离紫竹园66米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":582,"SiteName":"8号线永清街地铁","Address":"湖北省;武汉市;江岸区,距离芦沟桥路82米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":583,"SiteName":"21号线新荣村站","Address":"湖北省;武汉市;江岸区,距离堤角小学76米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":590,"SiteName":"国际大都会","Address":"湖北省;武汉市;江岸区,距离余华岭73米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":592,"SiteName":"21号线百步亭地铁站","Address":"湖北省;武汉市;江岸区,距离秀泽园北区136米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":595,"SiteName":"21号线后湖大道地铁","Address":"湖北省;武汉市;江岸区,距离武商量贩怡和苑店251米","Distance":null,"DepartmentId":65}]}
     * Code : 0
     * Message : ok
     */
    
    private DataBean Data;
    private int Code;
    private String Message;
    
    public DataBean getData() { return Data;}
    
    public void setData(DataBean Data) { this.Data = Data;}
    
    public int getCode() { return Code;}
    
    public void setCode(int Code) { this.Code = Code;}
    
    public String getMessage() { return Message;}
    
    public void setMessage(String Message) { this.Message = Message;}
    
    public static class DataBean
    {
        /**
         * TotalSite : 477
         * SiteModel : [{"SiteType":4,"SiteId":574,"SiteName":"海伦春天三期工地","Address":"湖北省;武汉市;汉阳区,距离神龙驾校第五招生培训处42米","Distance":null,"DepartmentId":93},{"SiteType":4,"SiteId":575,"SiteName":"6号线香港路站","Address":"湖北省;武汉市;江岸区,距离上海家常菜23米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":579,"SiteName":"7号线三阳路站A","Address":"湖北省;武汉市;江岸区,距离武汉利行旅行社13米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":580,"SiteName":"8号线竹叶山站","Address":"湖北省;武汉市;江岸区,距离天梨SOHO102米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":581,"SiteName":"8号线幸福大道","Address":"湖北省;武汉市;江岸区,距离紫竹园66米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":582,"SiteName":"8号线永清街地铁","Address":"湖北省;武汉市;江岸区,距离芦沟桥路82米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":583,"SiteName":"21号线新荣村站","Address":"湖北省;武汉市;江岸区,距离堤角小学76米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":590,"SiteName":"国际大都会","Address":"湖北省;武汉市;江岸区,距离余华岭73米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":592,"SiteName":"21号线百步亭地铁站","Address":"湖北省;武汉市;江岸区,距离秀泽园北区136米","Distance":null,"DepartmentId":65},{"SiteType":4,"SiteId":595,"SiteName":"21号线后湖大道地铁","Address":"湖北省;武汉市;江岸区,距离武商量贩怡和苑店251米","Distance":null,"DepartmentId":65}]
         */
        
        private int TotalSite;
        private List<SiteModelBean> SiteModel;
        
        public int getTotalSite() { return TotalSite;}
        
        public void setTotalSite(int TotalSite) { this.TotalSite = TotalSite;}
        
        public List<SiteModelBean> getSiteModel() { return SiteModel;}
        
        public void setSiteModel(List<SiteModelBean> SiteModel) { this.SiteModel = SiteModel;}
        
        public static class SiteModelBean
        {
            /**
             * SiteType : 4
             * SiteId : 574
             * SiteName : 海伦春天三期工地
             * Address : 湖北省;武汉市;汉阳区,距离神龙驾校第五招生培训处42米
             * Distance : null
             * DepartmentId : 93
             */
            
            private int SiteType;
            private int SiteId;
            private String SiteName;
            private String Address;
            private Object Distance;
            private int DepartmentId;
            
            private boolean isChecked;
    
            public int getSiteType() { return SiteType;}
            
            public void setSiteType(int SiteType) { this.SiteType = SiteType;}
            
            public int getSiteId() { return SiteId;}
            
            public void setSiteId(int SiteId) { this.SiteId = SiteId;}
            
            public String getSiteName() { return SiteName;}
            
            public void setSiteName(String SiteName) { this.SiteName = SiteName;}
            
            public String getAddress() { return Address;}
            
            public void setAddress(String Address) { this.Address = Address;}
            
            public Object getDistance() { return Distance;}
            
            public void setDistance(Object Distance) { this.Distance = Distance;}
            
            public int getDepartmentId() { return DepartmentId;}
            
            public void setDepartmentId(int DepartmentId) { this.DepartmentId = DepartmentId;}
    
            public boolean isChecked()
            {
                return isChecked;
            }
    
            public void setChecked(boolean checked)
            {
                isChecked = checked;
            }
        }
    }
}
