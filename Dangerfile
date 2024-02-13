# Android Lint output check
lint_dir = "**/build/reports/lint-results-debug.xml"
Dir[lint_dir].each do |file_name|
  android_lint.skip_gradle_task = true
  android_lint.report_file = file_name
  android_lint.lint(inline_mode: true)
end

message("Using: #{ENV["ANDROID_HOME"]}/cmdline-tools/latest/bin/apkanalyzer")
#apkstats.apkanalyzer_path="#{ENV["ANDROID_HOME"]}/cmdline-tools/latest/bin/apkanalyzer"
#apkstats.apk_filepath='app/build/outputs/apk/debug/app-debug.apk'
#message(apkstats.file_size)
#message(apkstats.download_size)
#message(apkstats.required_features)
#message(apkstats.non_required_features)
#message(apkstats.permissions)
#message(apkstats.min_sdk)
#message(apkstats.target_sdk)
#message("#{apkstats.method_reference_count}")
