package model;

public class Poupanca extends Conta {
  private double juros;

  public Poupanca(int numero, Banco banco, Cliente cliente, double saldo, double juros) {
    super(numero, banco, cliente, saldo);
    this.juros = juros;
  }

  public double getJuros() {
    return juros;
  }

  public void setJuros(double juros) {
    this.juros = juros;
  }

  public void renderJuros() {
    double n = (this.juros / 100) + 1;
    this.setSaldo(this.getSaldo() * n);
  }
}
