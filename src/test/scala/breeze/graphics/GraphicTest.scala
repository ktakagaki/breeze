package breeze.graphics

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.{Group, Scene}
import breeze.graphics.graphs.Graphic

/**
 * @author ktakagaki
 * @date 1/31/14.
 */
object GraphicTest extends JFXApp {

  val canvas = new Graphic()//(width, height)
  //  canvas.draw()

//  val rootGr = new Group(canvas)


//  stage = new JFXApp.PrimaryStage {
//    title = "KKGraphicsTest"
//    scene = new Scene(400, 400){
//      content = rootGr
//    }
//  }



  //  val fileChooser = new scalafx.stage.FileChooser
  //  //val extFilter = new ExtensionFilter
  //  fileChooser.showOpenDialog(stage)

}