# Android Lint output check
lint_dir = "**/build/reports/lint-results-debug.xml"
Dir[lint_dir].each do |file_name|
  android_lint.skip_gradle_task = true
  android_lint.report_file = file_name
  android_lint.lint(inline_mode: true)
end

apkanalyzer.apk_file = "app/build/outputs/apk/debug/app-debug.apk"
apkanalyzer.file_size
apkanalyzer.permissions
