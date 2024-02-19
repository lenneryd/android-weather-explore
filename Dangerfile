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
message("Download Size: #{convert_size(apkstats.download_size)}")
#message(apkstats.required_features)
#message(apkstats.non_required_features)
message("Permissions: #{apkstats.permissions.map { |perm| perm.sub("android.permission.", "") }.map { |str| "[#{str}](https://developer.android.com/reference/android/Manifest.permission##{str})"  } }")
message("Min SDK: [#{apkstats.min_sdk}](https://apilevels.com/)")
message("Target SDK: [#{apkstats.target_sdk}](https://apilevels.com/)")
#message("#{apkstats.method_reference_count}")

# options are ["warning", error "error"]
kotlin_detekt.skip_gradle_task = true
kotlin_detekt.severity = "warning"
kotlin_detekt.report_file = "app/build/reports/detekt/detekt.xml"
kotlin_detekt.detekt