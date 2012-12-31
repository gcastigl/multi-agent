package ar.edu.itba.sia.init;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class AgentCmdAgentLineArgument implements CommandLineArgument {

	private String name;
	private Class<?> clazz;
	private String[] args;

	public AgentCmdAgentLineArgument(String name, Class<?> clazz, String... args) {
		this.name = name;
		this.clazz = clazz;
		this.args = args;
	}

	@Override
	public String getCommand() {
		String cmdLine = name + ":" + clazz.getCanonicalName();
		if (!ArrayUtils.isEmpty(args)) {
			cmdLine += "(" + StringUtils.join(args, ",") + ")";
		}
		return cmdLine;
	}
}
