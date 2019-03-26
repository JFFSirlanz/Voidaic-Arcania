package com.camellias.voidaicarcania.core.kofi;

import java.util.HashMap;
import java.util.UUID;

public class Donators
{
	public static final HashMap<UUID, Integer> DONATOR_ID = new HashMap<UUID, Integer>();
	
	public static final UUID TigersHideout = UUID.fromString("01155b6d-2338-4513-909c-224ddcc0214f");
	public static final UUID DanTheDetermined = UUID.fromString("3810639e-b52f-4df1-ad4b-748236bf77b9");
	public static final UUID Cacti_Farmer = UUID.fromString("a3b83f0c-8b16-47dd-8197-d6eedd9f1a5b");
	public static final UUID Smaugnolia = UUID.fromString("a5e5b8ef-5e25-4e4d-801b-4717c4d3c91c");
	//Mysterious Stranger who I can't put here because idk who they are ;-;
	public static final UUID Caridos = UUID.fromString("6ddb3192-063f-4078-87c5-9f51ad0e8ca2");
	public static final UUID MechaJ = UUID.fromString("b8870124-707a-492a-a893-809b6ff39c22");
	public static final UUID spinyshell = UUID.fromString("43ddc09a-fa14-4706-a5dc-9c7490497ff9");
	public static final UUID Brosingr = UUID.fromString("38e31ac9-0fb3-4f1e-ad92-2d120028e0bc");
	
	public static void addDonators()
	{
		DONATOR_ID.put(TigersHideout, 1);
		DONATOR_ID.put(DanTheDetermined, 1);
		DONATOR_ID.put(Cacti_Farmer, 5);
		DONATOR_ID.put(Smaugnolia, 1);
		//Mysterious Stranger who I can't put here because idk who they are ;-;
		DONATOR_ID.put(Caridos, 4);
		DONATOR_ID.put(MechaJ, 8);
		DONATOR_ID.put(spinyshell, 6);
		DONATOR_ID.put(Brosingr, 1);
	}
}
