package com.petapp.model;

public enum Raca {
	BEAGLE("Beagle"),
	BORDERCOLLIE("Border Collie"),
	BULDOGUEINGLES("Buldogue Inglês"),
	BULLDOGFRANCES("Bulldog Francês"),
	CHIHUAHUA("Chihuahua"),
	CHOWCHOW("Chow Chow"),
	COCKERSPANIEL("Cocker Spaniel"),
	DACHSHUND("Dachshund"),
	GOLDENRETRIEVER("Golden Retriever"),
	HUSKYSIBERIANO("Husky Siberiano"),
	LABRADOR("Labrador"),
	LHASAAPSO("Lhasa Apso"),
	LULUDAPOMERANIA("Lulu Da Pomerânia"),
	MALTES("Maltês"),
	OUTROS("Outros"),
	PINSCHER("Pinscher"),
	PITBULL("Pit Bull"),
	POODLE("Poodle"),
	PUG("Pug"),
	SCHNAUZER("Schnauzer"),
	SEMRACADEFINIDA("Sem Raça Definida"),
	SHARPEI("Shar-Pei"),
	SHIHTZU("Shih Tzu"),
	SPITZALEMAO("Spitz Alemão"),
	YORKSHIRE("Yorkshire");

	public String raca;

	private Raca(String raca) {
		this.raca = raca;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}
	
}