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
    EmLavagem(4),
    LavagemConcluida(5),
    Concluido(6), 
    Cancelado(7);

    private final int codigo;

    EnumStatus(int codigo) {
        this.codigo = codigo;
    }

    int codigo() {
        return codigo;
    }

    public static EnumStatus porCodigo(int codigo) {
        for (EnumStatus status : EnumStatus.values()) {
            if (codigo == status.codigo()) {
                return status;
            }
        }
        throw new IllegalArgumentException("codigo invalido");
    }

}
