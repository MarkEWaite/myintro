package org.jenkinsci.plugins.myintro;

import hudson.Launcher;
import hudson.Extension;
import hudson.FilePath;
import hudson.util.FormValidation;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import jenkins.tasks.SimpleBuildStep;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import org.kohsuke.stapler.DataBoundSetter;

/**
 * Sample {@link hudson.tasks.Builder}.
 *
 * <p>
 * When the user configures the project and enables this builder,
 * {@link org.jenkinsci.plugins.myintro.HelloWorldBuilder.DescriptorImpl#newInstance(StaplerRequest)} is invoked and a new
 * {@link org.jenkinsci.plugins.myintro.HelloWorldBuilder} is created. The created instance is persisted to
 * the project configuration XML by using XStream, so this allows you to use
 * instance fields (like {@link #name}) to remember the configuration.
 *
 * <p>
 * When a build is performed, the {@link #perform} method will be invoked.
 *
 * @author Kohsuke Kawaguchi
 * @version $Id: $Id
 */
public class HelloWorldBuilder extends Builder implements SimpleBuildStep {

    private final String name;
    private long sleepTime;

    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"

    /**
     * Builder that will echo "Hello " + name to the console log.
     *
     * @param name name to be displayed in the build log
     */
    @DataBoundConstructor
    public HelloWorldBuilder(String name) {
        this.name = name;
    }

    /**
     * Number of milliseconds to sleep.
     *
     * @param sleepTime milliseconds to sleep
     */
    @DataBoundSetter
    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    /**
     * Milliseconds to sleep.
     *
     * @return milliseconds to sleep
     */
    public long getSleepTime() {
        return sleepTime;
    }

    /**
     * We'll use this from the {@code config.jelly}.
     *
     * @return name to be displayed
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     *
     * Perform the actions for this build step.
     */
    @Override
    public void perform(Run<?, ?> build, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException {
        // This is where you 'build' the project.
        // Since this is a dummy, we just say 'hello world' and call that a build.

        // This also shows how you can consult the global configuration of the builder
        if (getDescriptor().getUseFrench()) {
            listener.getLogger().println("Bonjour, " + name + "!");
        } else {
            listener.getLogger().println("Hello, " + name + "!");
        }
        if (sleepTime > 0) {
            listener.getLogger().println("Sleeping for " + sleepTime / 1000.0 + " seconds");
            Thread.sleep(sleepTime);
        }
    }

    // Overridden for better type safety.
    // If your plugin doesn't really define any property on Descriptor,
    // you don't have to do this.

    /**
     * {@inheritDoc}
     *
     * Returns the descriptor.
     */
    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    /**
     * Descriptor for {@link HelloWorldBuilder}. Used as a singleton. The class
     * is marked as public so that it can be accessed from views.
     *
     * <p>
     * See
     * {@code src/main/resources/hudson/plugins/hello_world/HelloWorldBuilder/*.jelly}
     * for the actual HTML fragment for the configuration screen.
     */
    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        /**
         * To persist global configuration information, simply store it in a
         * field and call save().
         *
         * <p>
         * If you don't want fields to be persisted, use {@code transient}.
         */
        private boolean useFrench;

        /**
         * In order to load the persisted global configuration, you have to call
         * load() in the constructor.
         */
        public DescriptorImpl() {
            load();
        }

        /**
         * Performs on-the-fly validation of the form field 'name'.
         *
         * @param value This parameter receives the value that the user has
         * typed.
         * @return Indicates the outcome of the validation. This is sent to the
         * browser.
         * <p>
         * Note that returning {@link FormValidation#error(String)} does not
         * prevent the form from being saved. It just means that a message will
         * be displayed to the user.
         * @throws java.io.IOException
         * @throws javax.servlet.ServletException
         */
        public FormValidation doCheckName(@QueryParameter String value)
                throws IOException, ServletException {
            if (value.length() == 0) {
                return FormValidation.error("Please set a name");
            }
            if (value.length() < 4) {
                return FormValidation.warning("Isn't the name too short?");
            }
            return FormValidation.ok();
        }

        /**
         *
         * @param value
         * @return
         */
        public FormValidation doCheckSleepTime(@QueryParameter String value) {
            long sleepTime;
            try {
                sleepTime = Long.valueOf(value);
            } catch (NumberFormatException nfe) {
                return FormValidation.error("Sleep time must be a number");
            }
            if (sleepTime < 0) {
                return FormValidation.error("Sleep time must be a positive integer");
            }
            if (sleepTime > 10 * 60 * 1000) {
                return FormValidation.error("Sleep time must be less than 10 minutes");
            }
            if (sleepTime > 1 * 60 * 1000) {
                return FormValidation.warning("Sleep time should be less than 1 minute");
            }
            return FormValidation.ok();
        }

        /**
         *
         * @param aClass
         * @return
         */
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            // Indicates that this builder can be used with all kinds of project types 
            return true;
        }

        /**
         * This human readable name is used in the configuration screen.
         * @return 
         */
        public String getDisplayName() {
            return "Say hello world";
        }

        /**
         *
         * @param req
         * @param formData
         * @return
         * @throws FormException
         */
        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            // To persist global configuration information,
            // set that to properties and call save().
            useFrench = formData.getBoolean("useFrench");
            // ^Can also use req.bindJSON(this, formData);
            //  (easier when there are many fields; need set* methods for this, like setUseFrench)
            save();
            return super.configure(req, formData);
        }

        /**
         * This method returns true if the global configuration says we should
         * speak French.
         *
         * The method name is bit awkward because global.jelly calls this method
         * to determine the initial state of the checkbox by the naming
         * convention.
         * @return 
         */
        public boolean getUseFrench() {
            return useFrench;
        }
    }
}
