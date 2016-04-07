package com.momnop.modularsonics.api;

public enum ModuleType {
	
	SCREWDRIVER ("Sonic Screwdriver"),
	BLASTER ("Sonic Blaster");
	
	private final String moduleType;

    /**
     * @param moduleType
     */
    private ModuleType(final String moduleType) {
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
