# Android Lint output check
lint_dir = "**/build/reports/lint-results-debug.xml"
Dir[lint_dir].each do |file_name|
  android_lint.skip_gradle_task = true
  android_lint.report_file = file_name
  android_lint.lint(inline_mode: true)
end

def convert_size(bytes)
  size = ""
  if bytes > 1024 * 1024
    size = "#{(bytes.to_f / (1024 * 1024)).round} MB"
  elsif bytes > 1024
    size = "#{(bytes.to_f / 1024).round} KB"
  else
    size = "#{bytes} b"
  end
  size
end

message("Using: #{ENV["ANDROID_HOME"]}/cmdline-tools/latest/bin/apkanalyzer")
apkstats.apkanalyzer_path="#{ENV["ANDROID_HOME"]}/cmdline-tools/latest/bin/apkanalyzer"
apkstats.apk_filepath='./app/build/outputs/apk/debug/app-debug.apk'
message("Size: #{convert_size(apkstats.file_size)}")
message("Download Size: #{apkstats.download_size}")
#message(apkstats.required_features)
#message(apkstats.non_required_features)
message("Features: #{apkstats.permissions}")
message("Min SDK: #{apkstats.min_sdk}")
message("Target SDK: #{apkstats.target_sdk}")
#message("#{apkstats.method_reference_count}")
