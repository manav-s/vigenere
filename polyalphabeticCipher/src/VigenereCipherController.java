public class VigenereCipherController {

  private CipherModelInterface model;
  private Readable rd;
  private Appendable ap;
  private VigenereCipherView view;

  public VigenereCipherController(CipherModelInterface model, Readable rd, Appendable ap) {
    this.model = model;
    this.rd = rd;
    this.ap = ap;
    this.view = new VigenereCipherView();
  }



}
