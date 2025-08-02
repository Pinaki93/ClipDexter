# Quick Screenshot Capture Guide

## 🚀 Quick Start

1. **Run the application**:
   ```bash
   ./gradlew run
   ```

2. **Wait for app to load** (about 5-10 seconds)

3. **Capture screenshots** using the commands below

## 📸 Capture Commands

### Main List Screen
```bash
# Capture main interface (adjust coordinates as needed)
screencapture -R 100,100,800,600 docs/images/main-list-screen.png
```

### Script Editor Screen
1. Click "Edit" on any utility in the app
2. Wait for editor to load
3. Capture:
```bash
screencapture -R 100,100,800,600 docs/images/detail-edit-screen.png
```

### Compact View
```bash
# Capture smaller window
screencapture -R 150,150,600,450 docs/images/compact-view.png
```

## 🎯 Tips for Best Results

### Before Capturing
- **Clean your desktop** - Remove unnecessary icons
- **Use consistent window size** - Keep app window at 800x600
- **Good lighting** - Ensure screen is well-lit
- **High contrast** - Use dark mode or light mode consistently

### During Capture
- **Focus on features** - Highlight the main functionality
- **Show multiple items** - Capture 3-4 utilities in the list
- **Include buttons** - Make sure "Run" and "Edit" buttons are visible
- **Clean interface** - Avoid cluttered backgrounds

### After Capture
- **Crop if needed** - Remove unnecessary borders
- **Check quality** - Ensure text is readable
- **Optimize size** - Keep files under 500KB
- **Test on GitHub** - Verify images look good

## 🔧 Alternative Methods

### Interactive Capture (macOS)
```bash
# Select area manually
screencapture -i docs/images/main-list-screen.png
```

### Window Capture (macOS)
```bash
# Capture specific window
screencapture -W docs/images/main-list-screen.png
```

### Using Preview (macOS)
1. Take screenshot with `Cmd + Shift + 4`
2. Open in Preview
3. Crop and resize as needed
4. Save as PNG in `docs/images/`

## 📁 File Structure
```
docs/
└── images/
    ├── main-list-screen.png     # Main interface
    ├── detail-edit-screen.png   # Script editor
    └── compact-view.png         # Compact view
```

## ✅ Quality Checklist
- [ ] Images are crisp and clear
- [ ] Text is readable
- [ ] File size < 500KB
- [ ] No personal data visible
- [ ] Consistent window sizes
- [ ] Good contrast and lighting

---

**Ready to capture?** Run the app and use these commands to create professional screenshots! 📸 