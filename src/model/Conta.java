
package model;

public class Conta {
  private int numero;
  private Banco banco;
  private Cliente cliente;
  private double saldo = 0;

  public Conta(int numero, Banco banco, Cliente cliente, double saldo){
    this.numero = numero;
    this.banco = banco;
    this.cliente = cliente;
    this.saldo = saldo;
  }

  public int getNumero(){
    return this.numero;
  }


  public void setNumero(int numero){
    this.numero = numero;
  }

  public Banco getBanco(){
    return this.banco;
  }

  public void setBanco(Banco banco){
    this.banco = banco;
  }

  public Cliente getCliente(){
    return this.cliente;
  }

  public void setCliente(Cliente cliente){
    this.cliente = cliente;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }
}