package com.biblioteca.servico_usuarios;

public class Locatario extends Usuario {
    private float saldoDevedor;

    public Locatario(String id, String nome, String email, String telefone, float saldoDevedor) {
        super(id, nome, email, telefone);
        this.saldoDevedor = saldoDevedor;
    }
    
    public float getSaldoDevedor() {
        return saldoDevedor;
    }
    
    @Override
    public boolean verificarInadiplencia() {
        return saldoDevedor > 100;
    }

    public void atualizarSaldoDevedor(float valor) {
        float novoSaldo = saldoDevedor - (valor);
        if (novoSaldo < 0) {
            novoSaldo = 0;
        }
        saldoDevedor = novoSaldo;
    }

    @Override
    public String toString() {
        return "Locatario [saldoDevedor=" + saldoDevedor + "]";
    }

}