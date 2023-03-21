package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "file:environmentProperties/${env}.properties" })
public interface Environment extends Config {

	@Key("App.Url")
	String appUrl();

	@Key("App.User")
	String appUsername();

	@Key("App.Pass")
	String appPass();

	@Key("DB.Host")
	String dbHostname();

	@Key("DB.User")
	String dbUser();

	@Key("DB.Pass")
	String dbPass();
}
