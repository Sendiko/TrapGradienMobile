---
name: TrapGradien Narrative
colors:
  surface: '#f8faf9'
  surface-dim: '#d8dada'
  surface-bright: '#f8faf9'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f2f4f3'
  surface-container: '#eceeed'
  surface-container-high: '#e6e9e8'
  surface-container-highest: '#e1e3e2'
  on-surface: '#191c1c'
  on-surface-variant: '#42493f'
  inverse-surface: '#2e3131'
  inverse-on-surface: '#eff1f0'
  outline: '#72796e'
  outline-variant: '#c2c9bc'
  surface-tint: '#3e6837'
  primary: '#275022'
  on-primary: '#ffffff'
  primary-container: '#3e6837'
  on-primary-container: '#b5e5a8'
  inverse-primary: '#a4d397'
  secondary: '#526442'
  on-secondary: '#ffffff'
  secondary-container: '#d2e6bc'
  on-secondary-container: '#576846'
  tertiary: '#394949'
  on-tertiary: '#ffffff'
  tertiary-container: '#516161'
  on-tertiary-container: '#cadcdb'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#bff0b1'
  primary-fixed-dim: '#a4d397'
  on-primary-fixed: '#002201'
  on-primary-fixed-variant: '#275022'
  secondary-fixed: '#d5e9bf'
  secondary-fixed-dim: '#b9cda4'
  on-secondary-fixed: '#111f05'
  on-secondary-fixed-variant: '#3b4c2c'
  tertiary-fixed: '#d4e6e5'
  tertiary-fixed-dim: '#b8cac9'
  on-tertiary-fixed: '#0e1e1e'
  on-tertiary-fixed-variant: '#3a4a49'
  background: '#f8faf9'
  on-background: '#191c1c'
  surface-variant: '#e1e3e2'
typography:
  headline-lg:
    fontFamily: Inter
    fontSize: 48px
    fontWeight: '700'
    lineHeight: 56px
    letterSpacing: -0.02em
  headline-lg-mobile:
    fontFamily: Inter
    fontSize: 32px
    fontWeight: '700'
    lineHeight: 40px
  headline-md:
    fontFamily: Inter
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
  body-lg:
    fontFamily: Inter
    fontSize: 18px
    fontWeight: '400'
    lineHeight: 28px
  body-md:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  label-data:
    fontFamily: JetBrains Mono
    fontSize: 14px
    fontWeight: '500'
    lineHeight: 20px
    letterSpacing: 0.05em
  label-caps:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '700'
    lineHeight: 16px
    letterSpacing: 0.1em
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  base: 8px
  container-padding-mobile: 16px
  container-padding-desktop: 40px
  gutter: 24px
  card-gap: 20px
---

## Brand & Style

The design system is built on a "Tech-Organic" philosophy, merging the precision of IoT telemetry with the refreshing sensory experience of clean air. The target audience includes health-conscious homeowners and facility managers who require professional-grade data visualization that feels calming rather than overwhelming.

The visual style is **Corporate Modern with Glassmorphic accents**. It utilizes a predominantly light, airy interface to evoke a sense of oxygen and space, while using deep forest greens to anchor the brand in nature and reliability. Elements are characterized by soft depth, subtle translucency, and high-precision typography to ensure that complex environmental data remains legible and trustworthy.

## Colors

This design system uses a curated palette of botanical greens to represent air quality tiers. The **Primary Forest Green (#3E6837)** serves as the baseline for "Standard Pure Air" and primary actions.

- **Backgrounds:** Use a crisp white or the "Neutral" off-white to maintain a sense of cleanliness.
- **Glass Effects:** Use semi-transparent white (#FFFFFF80) for overlays to create the "mist" or "vapor" effect.
- **Functional Status:**
    - **Level 0 (Standby):** Neutral Gray.
    - **Level 1 (Low):** Soft Mint.
    - **Level 2 (Medium):** Brand Forest Green.
    - **Level 3 (High):** Vibrant Emerald.
    - **Level 4 (Turbo):** Deep, intense Spruce Green.

## Typography

The system utilizes **Inter** for all UI and editorial content due to its exceptional legibility at small sizes and modern, neutral character. To emphasize the "High-Tech" nature of the IoT platform, **JetBrains Mono** is introduced specifically for telemetry readouts, sensor data, and numerical timestamps.

- **Headlines:** Should be tight and bold, conveying authority and clarity.
- **Data Labels:** Always use the monospaced font for sensor values to ensure that numbers don't jump horizontally when updating in real-time.
- **Hierarchy:** Use uppercase labels with increased letter spacing for category headers (e.g., "PARTICULATE MATTER").

## Layout & Spacing

The layout follows a **Fluid Grid** model with generous white space to reinforce the "breathable" brand attribute.

- **Desktop:** 12-column grid with 24px gutters. Dashboard widgets should span 3, 4, or 6 columns depending on data complexity.
- **Mobile:** Single column with 16px side margins.
- **Spacing Rhythm:** Based on an 8px linear scale. Metric groups within cards should use 8px spacing, while distinct sections should use 32px or 48px to allow the interface to "breathe."

## Elevation & Depth

Hierarchy is established through **Tonal Layering and Glassmorphism** rather than heavy shadows.

- **Surface 0 (Background):** Solid Neutral White (#F8FAF9).
- **Surface 1 (Cards):** Semi-transparent white with a 12px backdrop-blur. A 1px solid border in 10% Primary Green provides definition without heaviness.
- **Surface 2 (Active/Pop-overs):** Subtle ambient shadow (0px 8px 24px rgba(62, 104, 55, 0.08)) to indicate interaction.
- **Data Highlights:** Use inner glows rather than drop shadows to make gauges feel like they are illuminated from within, like modern hardware displays.

## Shapes

The shape language is **Rounded**, balancing the precision of the hardware with the organic nature of air.

- **Standard Components:** Buttons and inputs use a 0.5rem (8px) radius.
- **Large Components:** Data cards and dashboard containers use a 1.5rem (24px) radius to soften the technological feel.
- **Gauges:** Circular or semi-circular paths are preferred for real-time metrics to mirror the physical dials found on high-end air purifiers.

## Components

### Data Cards
Cards are the primary container for telemetry. They must feature a glassmorphic background with a subtle "noise" texture to mimic frosted glass. Titles should be in `label-caps` in the Primary Green.

### Real-Time Metric Gauges
Circular progress bars representing air quality. Use a thick stroke for the background (10% primary green) and a vibrant stroke for the current value using the appropriate Status Color.

### Buttons
- **Primary:** Solid #3E6837 with white text.
- **Secondary:** Ghost style with a 1px Primary Green border.
- **Status Pills:** Small, pill-shaped indicators for "Auto," "Manual," or "Turbo" modes, using the 0-4 Level color logic.

### Historical Line Charts
Lines should be rendered with a "Smooth" (Catmull-Rom) interpolation to feel organic. Use a gradient fill below the line that fades from the status color to transparent.

### Inputs & Toggles
Toggles should have a physical, tactile feel with a "squishy" animation upon state change, reinforcing the premium IoT hardware connection. Use the Primary Green for the "On" state.