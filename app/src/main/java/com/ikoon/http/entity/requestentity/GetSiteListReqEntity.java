package com.ikoon.http.entity.requestentity;

import java.util.List;

/**
 * @author MrKong
 * @date 2018/6/14
 * @description
 */

public class GetSiteListReqEntity
{
    /**
     * SiteName :
     * SiteTypes : [5]
     * DepartmentId : 3
     * DepartmentName : 武汉市
     * CenrtPoint : {"Lat":30.590873,"Lon":114.373201}
     * PageIndex : 1
     * PageSize : 10
     */
    
    private String SiteName;
    private int DepartmentId;
    private String DepartmentName;
    private CenrtPointBean CenrtPoint;
    private int PageIndex;
    private int PageSize;
    private List<Integer> SiteTypes;
    
    public String getSiteName()
    {
        return SiteName;
    }
    
    public void setSiteName(String SiteName)
    {
        this.SiteName = SiteName;
    }
    
    public int getDepartmentId()
    {
        return DepartmentId;
    }
    
    public void setDepartmentId(int DepartmentId)
    {
        this.DepartmentId = DepartmentId;
    }
    
    public String getDepartmentName()
    {
        return DepartmentName;
    }
    
    public void setDepartmentName(String DepartmentName)
    {
        this.DepartmentName = DepartmentName;
    }
    
    public CenrtPointBean getCenrtPoint()
    {
        return CenrtPoint;
    }
    
    public void setCenrtPoint(CenrtPointBean CenrtPoint)
    {
        this.CenrtPoint = CenrtPoint;
    }
    
    public int getPageIndex()
    {
        return PageIndex;
    }
    
    public void setPageIndex(int PageIndex)
    {
        this.PageIndex = PageIndex;
    }
    
    public int getPageSize()
    {
        return PageSize;
    }
    
    public void setPageSize(int PageSize)
    {
        this.PageSize = PageSize;
    }
    
    public List<Integer> getSiteTypes()
    {
        return SiteTypes;
    }
    
    public void setSiteTypes(List<Integer> SiteTypes)
    {
        this.SiteTypes = SiteTypes;
    }
    
    public static class CenrtPointBean
    {
        /**
         * Lat : 30.590873
         * Lon : 114.373201
         */
        
        private double Lat;
        private double Lon;
        
        public double getLat()
        {
            return Lat;
        }
        
        public void setLat(double Lat)
        {
            this.Lat = Lat;
        }
        
        public double getLon()
        {
            return Lon;
        }
        
        public void setLon(double Lon)
        {
            this.Lon = Lon;
        }
    }
}
