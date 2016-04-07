package com.momnop.modularsonics.api;

public enum SonicType {
	
	NINTH ("Ninth Sonic"),
	TENTH ("Tenth Sonic");
	
	private final String moduleType;

    /**
     * @param moduleType
     */
    private SonicType(final String moduleType) {
        this.moduleType = moduleType;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return moduleType;
    }
	
}
