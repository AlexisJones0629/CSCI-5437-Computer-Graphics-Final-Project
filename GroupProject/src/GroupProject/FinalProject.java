package GroupProject;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Material;
import javax.media.j3d.PointAttributes;
import javax.media.j3d.PointLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;  
import javax.vecmath.Point3f;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.PlatformGeometry;
import java.awt.AWTEvent;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;
import javax.imageio.*;
import java.net.URL;
import java.util.BitSet;
import java.util.Enumeration;
import java.util.Iterator;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Behavior;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.IndexedTriangleArray;
import javax.media.j3d.Light;
import javax.media.j3d.Node;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Switch;
import javax.media.j3d.SwitchValueInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color4f;
import javax.vecmath.Matrix4d;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
public class FinalProject extends Applet implements ActionListener {
   
   
  
	

	private TransformGroup tg = null;
        private TransformGroup tg2 = null;
        private TransformGroup tg3 = null;
        private TransformGroup tg4 = null;
        private TransformGroup tg5 = null;
        
	private Transform3D tr = null;
        private Transform3D tr2 = null;
        private Transform3D tr3 = null;
        private Transform3D tr4 = null; 
        private Transform3D tr5 = null;
   
        
        private Button meusumButton = new Button("Muesum");
        private Button diamondButton = new Button("Diamond");
        private Button statueButton = new Button("Emerald");
        private Button starButton = new Button("Pyramid");
        private Button pictureButton = new Button("Picture");
        
        protected Switch firstSwitch = new Switch(0);
     
        
        public static void main(String[] args) {
		new MainFrame(new FinalProject(), 900, 600);
	}
        
	public void init() {
		setLayout(new BorderLayout());
                
                Panel panel = new Panel();
                panel.setLayout(new GridLayout(5,1));
                add(panel, BorderLayout.EAST);
                meusumButton.addActionListener(this);
                panel.add(meusumButton);
                diamondButton.addActionListener(this);
                panel.add(diamondButton);
                statueButton.addActionListener(this);
                panel.add(statueButton);
                starButton.addActionListener(this);
                panel.add(starButton); 
                pictureButton.addActionListener(this);
                panel.add(pictureButton);
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();

	Canvas3D cv = new Canvas3D(gc);
		add(cv, BorderLayout.CENTER);
		SimpleUniverse su = new SimpleUniverse(cv);

		BranchGroup scene = createSceneGraph();
		su.getViewingPlatform().setNominalViewingTransform();

		su.getViewer().getView().setBackClipDistance(100.0);

		su.addBranchGraph(scene);
	}

	private BranchGroup createSceneGraph() {
		BranchGroup objRoot = new BranchGroup();
                 TransformGroup tg = new TransformGroup();
		BoundingSphere bounds = new BoundingSphere();

              
                // Loads in image to the background
                URL url = getClass().getClassLoader().getResource("Images/Museum.jpg");
                BufferedImage bi = null;
                try{
                    bi = ImageIO.read(url);
                }catch (IOException ex){
                    ex.printStackTrace();
                }
                ImageComponent2D image = new ImageComponent2D(ImageComponent2D.FORMAT_RGB, bi);
                Background background = new Background(image);
               
               background.setApplicationBounds(bounds);
               background.setImageScaleMode(3);
               objRoot.addChild(background);
		objRoot.addChild(createMeusum());
                
           
                //Adds light
                DirectionalLight dirlight = new DirectionalLight(true,new Color3f(1.0f,1.0f,1.0f), new Vector3f(-0.3f, 0.2f, -1.0f));
                dirlight.setInfluencingBounds(bounds);
                objRoot.addChild(dirlight);
                
		return objRoot;
	}

	private BranchGroup createMeusum() {

		BranchGroup objRoot = new BranchGroup();
             
        
                 tg = new TransformGroup();
                 tr = new Transform3D();
                
                 tg2 = new TransformGroup();
                 tr2 = new Transform3D();
                
                 tg3 = new TransformGroup();
                tr3 = new Transform3D();
              
                tg4 = new TransformGroup();
                tr4 = new Transform3D();
              
                tg5 = new TransformGroup();
                tr5 = new Transform3D();
               
		//Transform for the musuem 
                tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tr.setTranslation(new Vector3d(0.0, -3.5, -5.0));
		tr.setScale(2);
		tg.setTransform(tr);

                //Trasform for the diamond
                 tg2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
              tr2.setTranslation(new Vector3d(0f,-.3f,0.0f));
              tr2.setScale(.2);
              tg2.setTransform(tr2);
              
              //Transform for the emerald
              tg3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
              tr3.setTranslation(new Vector3d(0f,0f,0.0f));
              tr3.setScale(.2);
              tg3.setTransform(tr3);
              
              //Transform for the pryamid
              tg4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
               tr4.setTranslation(new Vector3d(0,0,0f));
               tr4.setScale(.4);
               tg4.setTransform(tr4);
               
               //Transform for the picture
               tg5.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
               tr5.setTranslation(new Vector3d(.4f,.3f,0f));
               tr5.setScale(.5);
              tg5.setTransform(tr5);
               
               
           // allows outside objest to be imported into Java3d by file  
	ObjectFile loader = new ObjectFile();
              
        //helps load the scene
		Scene s = null;
                Scene s2 = null;
               
              // files to be loaded into in the model folder
		File file = new java.io.File("model/Muesum2.obj");
		File file2 = new java.io.File("model/diamond.obj");
               
                try{
                   s = loader.load(file.toURI().toURL());
                   s2 = loader.load(file2.toURI().toURL());
                 
                } catch(Exception e){
                    System.err.println(e);
			System.exit(1);
                }
                //set appearance for picture geometry 
                 Appearance ap = createTextureAppearance();
                 PolygonAttributes pa = new PolygonAttributes(PolygonAttributes.POLYGON_FILL,
                 PolygonAttributes.CULL_NONE,0);
                ap.setPolygonAttributes(pa);
                Shape3D shape = new Shape3D(createGeometry(), ap);
                
                //set appearance for the pyramid
                Appearance ap2 = new Appearance();
                  ap2.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
                ap2.setPolygonAttributes(pa);
                Material mat = new Material(new Color3f(Color.yellow), new Color3f(Color.black), new Color3f(Color.yellow), new Color3f(Color.white), 70 );
                ap2.setMaterial(mat);
                mat.setShininess(300);
                Shape3D shape2  = new Shape3D(createGeometry2(), ap2);
                
                //set appearance for the emerald
                Appearance ap3 = new Appearance();
                ap3.setPolygonAttributes(pa);
                Material mat2 = new Material(new Color3f(Color.GREEN), new Color3f(Color.BLACK), new Color3f(Color.GREEN), new Color3f(Color.white), 30);
                ap3.setMaterial(mat2);
                Shape3D shape3 = new Shape3D(createGeometry3(), ap3);
                
                // add object to respective transformGroup 
                tg.addChild(s.getSceneGroup());
              tg2.addChild(s2.getSceneGroup());
              tg3.addChild(shape3);
              tg4.addChild(shape2);
              tg5.addChild(shape);
       
               BoundingSphere bounds = new BoundingSphere();
                 BoundingSphere bounds2 = new BoundingSphere();
                 BoundingSphere bounds3 = new BoundingSphere();
                 BoundingSphere bounds4 = new BoundingSphere();
          
            KeyNavigatorBehavior keyNav = new KeyNavigatorBehavior(tg);
            keyNav.setSchedulingBounds(bounds);
            objRoot.addChild(keyNav);
                
            //Mouse rotate for the diamond object 
                MouseRotate rotator = new MouseRotate(tg2);
                rotator.setSchedulingBounds(bounds);
                objRoot.addChild(rotator);
              
                // Mouse rotate for the hexagon object
                MouseRotate rotator2 = new MouseRotate(tg3);
                rotator2.setSchedulingBounds(bounds2);
                objRoot.addChild(rotator2);
          
                //Mouse Translate for the pyramid object
                MouseTranslate translate = new MouseTranslate(tg4);
                translate.setSchedulingBounds(bounds3);
                objRoot.addChild(translate);
              
                //Mouse zoom for the picture object
               MouseZoom zoom = new MouseZoom(tg5);
                zoom.setSchedulingBounds(bounds4);
                objRoot.addChild(zoom);
                
           
              firstSwitch.setCapability(Switch.ALLOW_SWITCH_WRITE);
             
             // add objects to switch 
             firstSwitch.addChild(tg);
             firstSwitch.addChild(tg2);
             firstSwitch.addChild(tg3);
             firstSwitch.addChild(tg4);
             firstSwitch.addChild(tg5);
              
             objRoot.addChild(firstSwitch);
		
                
                objRoot.compile();
                
		return objRoot;

	}
// creates the geometry for the picture
private Geometry createGeometry(){
    QuadArray picture = new QuadArray(4, GeometryArray.COORDINATES | GeometryArray.TEXTURE_COORDINATE_2);
    Point3f[] vertices = {
    new Point3f(-1f, 0f, 1f), 
    new Point3f(-1f,-1f,1f),
    new Point3f(0f,-1f,1f),
    new Point3f(0f,0f,1f) 
};
    picture.setCoordinates(0, vertices);
    TexCoord2f texture = new TexCoord2f();
    texture.set(0,1);
    picture.setTextureCoordinate(0, 0, texture);
    texture .set(0f,0f);
    picture.setTextureCoordinate(0, 1, texture);
    texture.set(1,0);
    picture.setTextureCoordinate(0, 2, texture);
    texture.set(1f,1f);
    picture.setTextureCoordinate(0, 3, texture);
   
    GeometryInfo gi = new GeometryInfo(picture);
   NormalGenerator ng = new NormalGenerator();
   ng.generateNormals(gi);
    return gi.getGeometryArray();
}
// creates the geomoetry for the pyramid 
private Geometry createGeometry2(){
    
   IndexedTriangleArray pyramid = new IndexedTriangleArray(5, GeometryArray.COORDINATES, 18);
   
   
Point3f[] vertices ={  
    new Point3f(0,1,0),
    new Point3f(1,0,1),
    new Point3f(-1,0,1),
    new Point3f(-1,0,-1),
    new Point3f(1,0,-1)};

pyramid.setCoordinates(0, vertices);
        int[] indices ={0,2,1,0,1,4,0,4,3,0,3,2,1,2,4,2,3,4};
    pyramid.setCoordinateIndices(0,indices);
    
    GeometryInfo gi = new GeometryInfo(pyramid);
    NormalGenerator ng = new NormalGenerator();
        ng.generateNormals(gi);
    return gi.getGeometryArray();
}
// creates the geometry for a hexagon 
  private Geometry createGeometry3(){
      
        GeometryInfo gi = new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
       Point3d[] vertices ={
           new Point3d(-.55,1,.2),
             new Point3d(.55f,1,.2),
           new Point3d(1f,0,.2),
            new Point3d(.55f,-1,.2f),
            new Point3d(-.55f,-1,.2),
           new Point3d(-1f,0,.1 ),
           
           new Point3d(-.55,1,-.2),
           new Point3d(.55f,1,-.2),
          new Point3d(1,0,-.2),
          new Point3d(.55f,-1,-.2),
            new Point3d(-.55f,-1,-.2),
           new Point3d(-1f,0,-.2)};
           int indices[] ={ 0,1,7,6,  1,2,8,7, 2,3,9,8, 3,4,10,9, 4,5,11,10, 5,0,6,11, 5,4,3,2,1,0, 6,7,8,9,10,11 };
            gi.setCoordinates(vertices);
        gi.setCoordinateIndices(indices);
        
        int[] stripCounts = {4,4,4,4,4,4,6,6};
        gi.setStripCounts(stripCounts);
           
       
       
        NormalGenerator ng = new NormalGenerator();
        ng.generateNormals(gi);
       return gi.getGeometryArray();
      
    
    }
//gets the image for said picture geometry 
Appearance createTextureAppearance(){
    Appearance ap = new Appearance();
    URL filename = getClass().getClassLoader().getResource("Images/Mona_Lisa.jpg");
    TextureLoader loader = new TextureLoader(filename, this);
    ImageComponent2D image = loader.getImage();
    if(image == null){
        System.out.println("Can't find texture file.");
    }
    Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA,
            image.getWidth(), image.getHeight());
    texture.setImage(0, image);
    texture.setMagFilter(Texture.BASE_LEVEL_LINEAR);
    ap.setTexture(texture);
    
    TextureAttributes texatt = new TextureAttributes();
    texatt.setTextureMode(TextureAttributes.COMBINE);
    ap.setTextureAttributes(texatt);
    ap.setMaterial(new Material());
    return ap; 
}


      //performs the Switch action to diplay the different objects
       
        public void actionPerformed(ActionEvent e){
            if(e.getSource() ==  meusumButton){
               firstSwitch.setWhichChild(0);
            }
            else if(e.getSource() == diamondButton){
                firstSwitch.setWhichChild(1);
            }
            else if(e.getSource() == statueButton ){
                firstSwitch.setWhichChild(2);
            }
            else if(e.getSource() == starButton){
                firstSwitch.setWhichChild(3);
            }
            else if(e.getSource() == pictureButton){
                firstSwitch.setWhichChild(4);
            }
        }
        
      

}