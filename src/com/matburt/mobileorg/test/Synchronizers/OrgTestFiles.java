package com.matburt.mobileorg.test.Synchronizers;

public class OrgTestFiles {

	public static class SimpleOrgFiles {
		public static final String indexFile = "#+READONLY\n"
				+ "#+TODO: TODO | DONE\n"
				+ "#+TAGS: { Home Computer Errands } \n"
				+ "#+ALLPRIORITIES: A B C\n"
				+ "* [[file:GTD.org][GTD.org]]\n";

		public static final String checksumsFile = "25aade750f6b60aa1df155fcbb357191  index.org\n"
				+ "42055316a0808ad634d7981653cf4400faddb91f  GTD.org";
		
		public static final String orgFileTopHeading = "top heading";
		public static final String orgFileChildHeading = "child heading";
		public static final String orgFile = "* " + orgFileTopHeading + "\n** " + orgFileChildHeading;
	}
	
	public static class ComplexOrgFiles {
		public static final String indexFile = "#+READONLY\n"
				+ "#+TODO: TODO NEXT PLAN RSCH GOAL DEFERRED WAIT | SOMEDAY CANC DONE\n"
				+ "#+TAGS: { Home Computer Online Phone Errands } { Agenda Read Listen Watch Code }\n"
				+ "#+ALLPRIORITIES: A B C\n"
				+ "* [[file:agendas.org][Agenda Views]]\n"
				+ "* [[file:GTD.org][GTD.org]]\n";

		public static final String checksumsFile = "25aade750f6b60aa1df155fcbb357191  index.org\n"
				+ "42055316a0808ad634d7981653cf4400faddb91f  GTD.org\n"
				+ "c864f7e1d6df18434738276a45c896cb  agendas.org";
		
		public static final String orgFile = "* new\n** test";
		public static final String agendasFile = "* new 1\n** test2";
	}
}
