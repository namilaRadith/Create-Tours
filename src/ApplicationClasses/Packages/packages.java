/*
 * To change this license header, choose License Headers in Project Properties.
 * To change  this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Packages;

/**
 *
 * @author Ayesh Dilan
 */
public abstract class packages {
    
    int package_ID;
    String packageName;
    String type;
    
    public packages(int packageID , String pName , String pType)
    {
            this.package_ID = packageID;
            this.packageName = pName;
            this.type = pType;
    }
    
    public abstract void insertPackages();
    public abstract void updatePackages();
    
    
}
