package org.kingback.suger.demo.Demo4IpCity;

//	130100: {ID: 1, CityCode: 1100, StandardCode: 130100, ScriptIndex: 0, AgencyCode: "冀A",
// ParentID: 12, Name: "石家庄", Domain: "sjz", Database: "shijiazhuang", Pinyin: "shijiazhuang",
// Location: "b114.51486,38.042307", RegionID: 3, AreaCode: "0311", NcRegionID: 100, TypeID: 2},
public class CityInfo {

    public int ID;
    public int CityCode;
    public int StandardCode;
    public int ScriptIndex;
    public String AgencyCode;
    public int ParentID;
    public String Name;
    public String Domain;
    public String Database;
    public String Pinyin;
    public String Location;
    public int RegionID;
    public String AreaCode;
    public int NcRegionID;
    public int TypeID;

    @Override
    public String toString() {
        return "CityInfo{" +
                "ID=" + ID +
                ", CityCode=" + CityCode +
                ", StandardCode=" + StandardCode +
                ", ScriptIndex=" + ScriptIndex +
                ", AgencyCode='" + AgencyCode + '\'' +
                ", ParentID=" + ParentID +
                ", Name='" + Name + '\'' +
                ", Domain='" + Domain + '\'' +
                ", Database='" + Database + '\'' +
                ", Pinyin='" + Pinyin + '\'' +
                ", Location='" + Location + '\'' +
                ", RegionID=" + RegionID +
                ", AreaCode='" + AreaCode + '\'' +
                ", NcRegionID=" + NcRegionID +
                ", TypeID=" + TypeID +
                '}';
    }
}
