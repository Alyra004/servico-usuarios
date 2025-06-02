package com.biblioteca.servico_usuarios;

public class Funcionario extends Usuario{
    private String cargo;
    private float salario;
    
    public Funcionario(String id, String nome, String email, String telefone, String cargo, float salario) {
        super(id, nome, email, telefone);
        this.cargo = cargo;
        this.salario = salario;
    }

    public float getSalario() {
        return salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario [cargo=" + cargo + ", salario=" + salario + "]";
    }

}
