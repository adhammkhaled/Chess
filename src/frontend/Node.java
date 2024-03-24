/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package frontend;

import backend.ChessCore.PawnPromotion;

/**
 *
 * @author adham
 */
public interface Node {
    public Node getParentNode();
    
    public void setParentNode(Node node);

    public void receivePromotionChoice(PawnPromotion selectedPromotion);
}
