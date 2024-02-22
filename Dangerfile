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
    size = "#{(bytes.to_f / (1024 * 1024)).round(2)} MB"
  elsif bytes > 1024
    size = "#{(bytes.to_f / 1024).round(2)} KB"
  else
    size = "#{bytes} b"
  end
  size
end

def permissions_list(permissions)
permissions.reject {
  | str | str == "com.cygni.tim.weatherexplore.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION"
}.map {
  | perm | perm.sub("android.permission.", "")
}.map {
  | str | "[#{str}](https://developer.android.com/reference/android/Manifest.permission##{str})"
}.join(separator = ", ")
end

apkstats.apkanalyzer_path="#{ENV["ANDROID_HOME"]}/cmdline-tools/latest/bin/apkanalyzer"
apkstats.apk_filepath='./app/build/outputs/apk/debug/app-debug.apk'
message("Size: #{convert_size(apkstats.file_size)}")
message("Download Size: #{convert_size(apkstats.download_size)}")
#message(apkstats.required_features)
#message(apkstats.non_required_features)
message("Permissions: #{permissions_list(apkstats.permissions)}")
message("Min SDK: [#{apkstats.min_sdk}](https://apilevels.com/)")
message("Target SDK: [#{apkstats.target_sdk}](https://apilevels.com/)")
#message("#{apkstats.method_reference_count}")

test_output = Dir.glob("**/build/outputs/androidTest-results/connected/TEST*.xml").first
junit.parse test_output
junit.report