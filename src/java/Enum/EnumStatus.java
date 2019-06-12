/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enum;

/**
 *
 * @author Guigo
 */
public enum EnumStatus {
    AguardandoPagamento(1),
    PagamentoConfirmado(2),
    EmLavagem(3),
    LavagemConcluida(4),
    Concluido(5),
    Cancelado(6),
    AguardandoColeta(7),
    EmEntrega(8),
    Entregue(9),
    NãoEntregue(10),
    EntregaCancelada(11);
    

    private final int code;
    
    EnumStatus(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
}
